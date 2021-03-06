package com.peng1m.education.controller;

import com.peng1m.education.model.FileResource;
import com.peng1m.education.repository.ResourceRepository;
import com.peng1m.education.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@ExposesResourceFor(FileResource.class)
public class FileController{
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private ResourceRepository resourceRepository;


    private FileResource uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String uuid = UUID.randomUUID().toString();
            String fileName = file.getOriginalFilename();
            fileStorageService.storeFile(file, uuid);
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/").path(uuid).toUriString();
            FileResource resource = resourceRepository.save(new FileResource(
                    uuid, fileName, downloadUri, file.getContentType(), file.getSize()));
            return resource;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Upload multiple files at single request
     *
     * @param files multiple files
     * @return List of FileResource
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    @PostMapping("upload")
    @ResponseBody
    public List<FileResource> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    /**
     * Download file from server
     *
     * @param uuid    uuid of file
     * @param request request
     * @return Resource
     */
    @GetMapping("download/{uuid:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String uuid, HttpServletRequest request) throws Exception {
        // Load file as resource
        Resource resource = fileStorageService.loadFileAsResource(uuid);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            LOGGER.info("Could not determine file type.");
        }
        // Fall back to default type if type cannot be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        String header = String.format("attachment; file=%s", resource.getFilename());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, header)
                .body(resource);
    }
}

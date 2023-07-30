package com.bhagwati.ContractManagement.service;

import com.bhagwati.ContractManagement.dto.VendorDto;
import com.bhagwati.ContractManagement.entity.Vendor;
import com.bhagwati.ContractManagement.mapper.VendorMapper;
import com.bhagwati.ContractManagement.repository.VendorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DocumentService {

    private final Path root = Paths.get("uploads");
    @Autowired
    private VendorsRepository vendorsRepository;

    @Autowired
    private VendorMapper vendorMapper;

    public Resource loadDocument(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public VendorDto getVendorById(String agreementId) {
        Vendor vendor = vendorsRepository.findById(agreementId).orElseThrow(() -> new RuntimeException("Data not found"));
        return vendorMapper.convertEntityToDto(vendor);
    }

    public Object saveDocument(MultipartFile file) throws IOException {
        Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        return true;
    }

    public boolean deleteVendor(String vendorId) {
        if (vendorsRepository.existsById(vendorId)) {
            vendorsRepository.deleteById(vendorId);
            return true;
        }
        return false;
    }


}

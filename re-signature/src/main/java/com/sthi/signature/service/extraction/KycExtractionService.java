package com.sthi.signature.service.extraction;

import com.sthi.signature.dto.KycExtractedData;
import org.springframework.web.multipart.MultipartFile;

public interface KycExtractionService {
    KycExtractedData extractFromImage(MultipartFile file, String docType);
}

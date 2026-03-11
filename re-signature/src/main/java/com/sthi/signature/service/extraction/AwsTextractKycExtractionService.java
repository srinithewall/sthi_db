package com.sthi.signature.service.extraction;

import com.sthi.signature.dto.KycExtractedData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service("awstextract")
public class AwsTextractKycExtractionService implements KycExtractionService {

    @Override
    public KycExtractedData extractFromImage(MultipartFile file, String docType) {
        log.info("AWS Textract stub called for {}", docType);
        throw new UnsupportedOperationException("AWS Textract extraction not yet implemented.");
    }
}

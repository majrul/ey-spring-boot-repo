package com.training;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class CloudStorageService implements StorageService {

}

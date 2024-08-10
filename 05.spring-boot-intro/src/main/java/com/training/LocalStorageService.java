package com.training;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"test", "dev"})
public class LocalStorageService implements StorageService {

}

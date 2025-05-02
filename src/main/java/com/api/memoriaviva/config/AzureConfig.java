package com.api.memoriaviva.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureConfig {
    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;

    //Constroi o cliente de serviço do Azure Blob Storage 
    @Bean
    public BlobServiceClient blobServiceClient() {
        return new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }

    // Cria ou recupera o cliente do container do Azure Blob Storage
    @Bean
    public BlobContainerClient blobContainerClient() { 
        BlobServiceClient serviceClient = blobServiceClient(); //Retoma o serviço
        BlobContainerClient containerClient = serviceClient.getBlobContainerClient(containerName); //Obtém o container
        
        // Se não existir, cria o container
        if (!containerClient.exists()) {
            containerClient.create();
        }
        
        return containerClient;
    }
}
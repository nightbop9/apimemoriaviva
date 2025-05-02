package com.api.memoriaviva.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobHttpHeaders;

@Service
/**
 * Esta classe trabalha em nível de serviço remotamente com o Azure Blob Storage.
 */
public class ImageService {
    @Autowired
    private BlobContainerClient containerClient; //Injeção pré-configurada do container via Bean

    //Separa a o nome do arquivo da url
    private String extractFileName(String imgUrl) {
        return imgUrl.substring(imgUrl.lastIndexOf("/") + 1); //Extrai toda a string após a ultima "/"
    }

    //Faz o upload e retorna o link vinculado
    public String configureImage(MultipartFile file) {
        try {
            //Gera um nome randômico para imagem
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            //Gera a referência a um blob
            BlobClient blobClient = containerClient.getBlobClient(fileName);

            //Troca o comportamento padrão de download do arquivo para visualização no navegador (configuração de cabeçalho)
            BlobHttpHeaders headers = new BlobHttpHeaders()
                    .setContentType(file.getContentType()) // Usa o tipo de conteúdo do arquivo
                    .setContentDisposition("inline"); // Força visualização no navegador

            /**
             * @Param file.getInputStream() O fluxo de entrada do arquivo
             * @Param file.getSize() O tamanho do arquivo
             * @Param true Se o arquivo exstir, deve ser sobrescrito (improvável pois o nome é gerado aleatoriamente)
             */
            blobClient.upload(file.getInputStream(), file.getSize(), true);

            // Aplica os cabeçalhos
            blobClient.setHttpHeaders(headers);
            return blobClient.getBlobUrl();
        } catch (IOException e) {
            throw new IllegalArgumentException("Erro ao fazer upload da imagem");
        }
    }

    /**
     * Método para salvar uma imagem.
     *
     * @param file a imagem a ser cadastrada
     * @return String a url da imagem
     */
    public String uploadImage(MultipartFile file) {
        return configureImage(file);
    }

    /**
     * Método para atualizar uma imagem
     *
     * @param imgUrl da imagem anterior
     * @param file   a imagem a ser cadastrada
     * @return String a url da nova imagem
     */
    public String updateImage(String imgUrl, MultipartFile file) {
        String fileName = extractFileName(imgUrl);
        containerClient.getBlobClient(fileName).deleteIfExists(); //Previne casos de erro onde a imagem é nula
        return configureImage(file);
    }

    /**
     * Método para excluir uma imagem
     *
     * @param imgUrl da imagem anterior
     */
    public void deleteImage(String imgUrl) {
        String fileName = extractFileName(imgUrl);
        containerClient.getBlobClient(fileName).deleteIfExists(); //Previne casos de erro onde a imagem é nula
    }
}
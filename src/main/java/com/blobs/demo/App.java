package com.blobs.demo;
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.*;
import java.io.*;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        if(args.length < 2){
            System.out.println("Error -- Invalid number of arguments");
            System.out.println("Usage: App [SAS_Key OR Account_Key] [A/S]");
            System.out.println("A == Account Key, S == SAS Key");
        }

        String key = null;
        if(args.length<=0){
            System.out.println(("Please pass either Storage Key or SAS key"));
            System.exit(0);
        }else{
            key = args[0];
            String type = args[1];
            App app = new App();
            
            if(type.indexOf("S") > -1)
                app.doSaSUpload(key);
            else
                app.doAccountKeyUpload(key);
        }
    }

    //Uploads a blob via a SAS key
    //Type == "S"
    public void doSaSUpload(String SASKey){
        SASKey = (SASKey.length()>0) ? SASKey : "<Enter your default key here>";
        System.out.println( "Azure Blob uplaod Demo using SAS Key" );
        

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().endpoint(SASKey).buildClient();
        doUpload(blobServiceClient);
    }

    public void doAccountKeyUpload(String key){
        key = (key.length() > 0) ? key : "<Enter your default key here>";
        System.out.println( "Azure Blob uplaod Demo using Account Key" );

        // Create a BlobServiceClient object which will be used to create a container client
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(key).buildClient();
        doUpload(blobServiceClient);
        
    }

    private void doUpload(BlobServiceClient blobServiceClient){
        String localPath = "./data/";
        String fileToUpload = "test10Gb.db";  //This is the file we're going to upload

        //Create a unique name for the container
        String containerName = "upload-demo";

        // Create the container and return a container client object
        BlobContainerClient containerClient = null;
        try{
            containerClient = blobServiceClient.createBlobContainer(containerName);
        }
        catch(Exception e){
            containerClient = blobServiceClient.getBlobContainerClient(containerName);
        }

        // Get a reference to a blob
        BlobClient blobClient = containerClient.getBlobClient(fileToUpload);
        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());

        // Upload the blob
        blobClient.uploadFromFile(localPath + fileToUpload, true);

        doListBlobs(containerClient);
    }

    public void doListBlobs(BlobContainerClient containerClient){
        System.out.println("\nListing blobs...");

        // List the blob(s) in the container.
        for (BlobItem blobItem : containerClient.listBlobs()) {
            System.out.println("\t" + blobItem.getName());
}
    }


}

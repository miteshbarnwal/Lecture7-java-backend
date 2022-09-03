package com.example.restapis;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ImageController {

    //Our backend api will produce the data in png format.
    //Whatever byte array we are returning here,just convert it into png format and return it to Frontend
//    @GetMapping(value="/image",produces = MediaType.IMAGE_PNG_VALUE)
//    @GetMapping(value="/image",produces = MediaType.IMAGE_GIF_VALUE)
    @GetMapping(value="/image",produces = MediaType.APPLICATION_PDF_VALUE)
    //We have accepted id from the frontend now we will build the url
    public byte[] getImage(@RequestParam("id") Integer id){
//        String url="https://picsum.photos/id/1/200/300";
        String url="https://picsum.photos/id/"+id+"/200/300";
        //we will have an object of Rest Template
        RestTemplate restTemplate = new RestTemplate();
        //first param : uri
        // second param : response type by the destination server (loreum picsum)
        byte[] image = restTemplate.getForObject(url, byte[].class);
        //we got the image with the help of restTemplate
        return image;


    }
}

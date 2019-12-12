package com.catcoderdev.auth.Controller;

import com.catcoderdev.auth.Model.ResponseHandler;
import com.catcoderdev.auth.Service.RequestHandlerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private ResponseHandler responseHandler;
    private HttpURLConnection connection;
    @Autowired
    private RequestHandlerServiceImpl restClient;
    public PostController() {
        this.responseHandler = new ResponseHandler();
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseHandler> getAll() {
        try {
            this.responseHandler.notFound();
            String responseData = this.restClient.get("http://localhost:8090/api/post/all/1");
            System.out.println(responseData);
            return new ResponseEntity<ResponseHandler>(
                    this.responseHandler,
                    HttpStatus.valueOf(this.responseHandler.getStatusCode())
            );
        }catch (Exception e){
            return this.errorHandler(e);
        }
    }

    private ResponseEntity<ResponseHandler> errorHandler(Exception e)
    {
        this.responseHandler.setError(true);
        this.responseHandler.setData(e.getMessage());
        this.responseHandler.setStatusCode(500);
        this.responseHandler.buildMetaData(0,0,0);
        return new ResponseEntity<ResponseHandler>(this.responseHandler,HttpStatus.valueOf(this.responseHandler.getStatusCode()));
    }
}

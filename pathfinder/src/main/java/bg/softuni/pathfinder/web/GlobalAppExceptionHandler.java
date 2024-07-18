package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.service.exception.UploadFileException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@ControllerAdvice
public class GlobalAppExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UploadFileException.class)
    public ModelAndView handleUploadException(UploadFileException ufe) {
        ModelAndView mav = new ModelAndView("error-template");
        mav.addObject("errorMessage", ufe.getMessage());

        return mav;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException() {
        ModelAndView mav = new ModelAndView("error-template");
        mav.addObject("errorMessage", "Error occurred while saving file!");

        return mav;
    }
}

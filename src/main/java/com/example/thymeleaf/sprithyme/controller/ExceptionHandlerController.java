package main.java.com.example.thymeleaf.sprithyme.controller;


@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView noSuchElementException(){
        return new ModelAndview ("exception").addObject("status", HTTPStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(){
        return new ModelAndView ("exception").addObject("status", HTTPStatus.INTERNAL_SERVER_ERROR.value());    
    }
    
}

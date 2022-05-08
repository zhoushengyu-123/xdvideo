package net.xdclass.xdvideo.exception;

import net.xdclass.xdvideo.domain.JsonData;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class XdExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData Handle456r(Exception e){
        e.printStackTrace();
        if(e instanceof XdException){
            XdException xdException =  (XdException)e;
            return JsonData.buildError(xdException.getMsg(),xdException.getCode());
        }else{
            return JsonData.buildError(e.getMessage());
        }
    }
}

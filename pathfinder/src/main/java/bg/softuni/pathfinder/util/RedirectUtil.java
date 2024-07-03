package bg.softuni.pathfinder.util;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class RedirectUtil {

    public static <T> void setRedirectAttrs(
            RedirectAttributes rAttrs, T bindingModel, BindingResult bindingResult, String attrName
    ) {
        rAttrs.addFlashAttribute(attrName, bindingModel);
        rAttrs.addFlashAttribute(
                "org.springframework.validation.BindingResult." + attrName,
                bindingResult);
    }
}

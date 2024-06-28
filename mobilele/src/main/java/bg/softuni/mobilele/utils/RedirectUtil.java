package bg.softuni.mobilele.utils;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class RedirectUtil {

    private final static String bindingResultLocation = "org.springframework.validation.BindingResult.";

    public static <T> void setRedirectBindingModelAndResult(
            RedirectAttributes rAttrs,
            T bindingModel,
            BindingResult bindingResult,
            String attrName
    ) {
        rAttrs.addFlashAttribute(attrName, bindingModel);
        rAttrs.addFlashAttribute(
            bindingResultLocation + attrName,
                bindingResult
        );
    }
}

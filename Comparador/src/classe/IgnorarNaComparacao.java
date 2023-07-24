package classe;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//permite criar a anotacao
@Target(ElementType.METHOD) // onde ser usado
public @interface IgnorarNaComparacao {

}

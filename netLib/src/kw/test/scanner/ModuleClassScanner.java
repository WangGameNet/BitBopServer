package kw.test.scanner;

import java.util.List;

public interface  ModuleClassScanner {
    List<Class<?>> scanClasses(String packagePath);
}

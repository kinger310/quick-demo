package com.wd.demo.spring6.self.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: wangd
 * @Date: 2023/6/2 9:58
 */
@Target(ElementType.FIELD
)
@Retention(RetentionPolicy.RUNTIME)
public @interface Di {
}

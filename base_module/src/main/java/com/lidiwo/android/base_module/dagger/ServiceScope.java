package com.lidiwo.android.base_module.dagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/11/15 20:04
 * @Company：智能程序员
 * @Description： *****************************************************
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ServiceScope {
}

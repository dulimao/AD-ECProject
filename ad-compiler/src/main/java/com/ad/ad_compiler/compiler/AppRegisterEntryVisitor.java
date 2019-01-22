package com.ad.ad_compiler.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

public class AppRegisterEntryVisitor extends SimpleAnnotationValueVisitor7<Void,Void> {

    private Filer mFiler;
    private TypeMirror mTypeMirror;
    private String mPackName;

    public void setFiler(Filer filer){
        this.mFiler = filer;
    }

    @Override
    public Void visitString(String s, Void p) {
        mPackName = s;
        return p;
    }

    @Override
    public Void visitType(TypeMirror t, Void p) {
        mTypeMirror = t;
        generateJavaCode();
        return p;
    }

    private void generateJavaCode(){
        TypeSpec targetActivity = TypeSpec.classBuilder("AppRegister")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror))
                .build();
        JavaFile javaFile = JavaFile.builder(mPackName + ".wxapi",targetActivity).addFileComment("微信广播接收器").build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

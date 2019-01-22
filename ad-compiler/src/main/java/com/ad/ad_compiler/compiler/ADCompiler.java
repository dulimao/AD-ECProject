package com.ad.ad_compiler.compiler;

import com.ad.ad_annotation.annotation.AppRegisterGenerator;
import com.ad.ad_annotation.annotation.EntryGenerator;
import com.ad.ad_annotation.annotation.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class ADCompiler extends AbstractProcessor {


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        Set<Class<? extends Annotation>> annotations = getSupportedAnnotation();
        for (Class<? extends Annotation> annotation : annotations) {
            types.add(annotation.getName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotation() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        generatorEntryCode(roundEnv);
        generatorPayEntryCode(roundEnv);
        generatorAppRegisterCode(roundEnv);
        return true;
    }



    //扫描文件
    private void scan(RoundEnvironment roundEnv, Class<? extends Annotation> annotation, AnnotationValueVisitor visitor) {
        for (Element typeElement : roundEnv.getElementsAnnotatedWith(annotation)) {
            List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();
            for (AnnotationMirror annotationMirror : annotationMirrors) {
                Map<? extends ExecutableElement, ? extends AnnotationValue> map = annotationMirror.getElementValues();
                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : map.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }
        }
    }

    private void generatorEntryCode(RoundEnvironment roundEnv){
        EntryVisitor visitor = new EntryVisitor();
        visitor.setFiler(processingEnv.getFiler());
        scan(roundEnv,EntryGenerator.class,visitor);
    }

    private void generatorPayEntryCode(RoundEnvironment roundEnv){
        PayEntryVisitor visitor = new PayEntryVisitor();
        visitor.setFiler(processingEnv.getFiler());
        scan(roundEnv,PayEntryGenerator.class,visitor);
    }

    private void generatorAppRegisterCode(RoundEnvironment roundEnv){
        AppRegisterEntryVisitor visitor = new AppRegisterEntryVisitor();
        visitor.setFiler(processingEnv.getFiler());
        scan(roundEnv,AppRegisterGenerator.class,visitor);
    }


}

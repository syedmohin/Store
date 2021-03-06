module AFCStore {
    exports com.afc;
    requires spring.boot.starter.data.jpa;
    requires spring.boot.starter.aop;
    requires spring.aop;
    requires org.aspectj.weaver;
    requires spring.boot.starter.jdbc;
    requires com.zaxxer.hikari;
    requires spring.jdbc;
    requires java.transaction;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires org.jboss.logging;
    requires javassist;
    requires net.bytebuddy;
    requires antlr;
    requires org.jboss.jandex;
    requires com.fasterxml.classmate;
    requires dom4j;
    requires org.hibernate.commons.annotations;
    requires org.glassfish.jaxb.runtime;
    requires java.xml.bind;
    requires com.sun.xml.txw2;
    requires com.sun.istack.runtime;
    requires spring.data.jpa;
    requires spring.data.commons;
    requires spring.orm;
    requires spring.context;
    requires spring.expression;
    requires spring.beans;
    requires spring.core;
    requires spring.jcl;
    requires org.slf4j;
    requires spring.aspects;
    requires spring.boot.starter.quartz;
    requires spring.boot.starter;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.boot.starter.logging;
    requires logback.classic;
    requires logback.core;
    requires org.apache.logging.slf4j;
    requires org.apache.logging.log4j;
    requires jul.to.slf4j;
    requires java.annotation;
    requires org.yaml.snakeyaml;
    requires spring.context.support;
    requires spring.tx;
    requires quartz;
    requires mchange.commons.java;
    requires javafx.controlsEmpty;
    requires javafx.controls;
    requires javafx.fxmlEmpty;
    requires javafx.fxml;
    requires javafx.baseEmpty;
    requires javafx.base;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
    requires javafx.mediaEmpty;
    requires javafx.media;
    requires com.jfoenix;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.core;
	requires org.kordamp.ikonli.materialdesign2;
    requires org.controlsfx.controls;
    requires javafx.weaver.spring.boot.starter;
    requires net.rgielen.fxweaver.spring;
    requires net.rgielen.fxweaver.core;
    requires net.rgielen.fxweaver.spring.boot.autoconfigure;
    requires fastexcel.reader;
    requires com.fasterxml.aalto;
    requires org.codehaus.stax2;
    requires org.apache.commons.compress;
    requires password4j;
}

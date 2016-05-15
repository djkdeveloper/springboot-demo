package com.djk.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by dujinkai on 2016/5/15.
 * Swagger api的配置文件实体
 */
@ConfigurationProperties(prefix = "djk.api-doc")
public class ApiDocProperties {


    /**
     * API文档标题
     */
    private String title = "Api Documentation";

    /**
     * API文档描述
     */
    private String description = "Api Documentation";

    /**
     * API文档版本
     */
    private String version = "1.0";

    private String termOfServiceUrl = "urn:tos";

    /**
     * 联系方式
     */
    private String contact = "Contact Email";

    /**
     * license限制
     */
    private String license = "Apache 2.0";

    /**
     * license限制详细地址
     */
    private String licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0";

    /**
     * API文档的包含路径，可配正则表达式，默认包含所有；
     */
    private String includePattern = ".*";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTermOfServiceUrl() {
        return termOfServiceUrl;
    }

    public void setTermOfServiceUrl(String termOfServiceUrl) {
        this.termOfServiceUrl = termOfServiceUrl;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getIncludePattern() {
        return includePattern;
    }

    public void setIncludePattern(String includePattern) {
        this.includePattern = includePattern;
    }

    @Override
    public String toString() {
        return "ApiDocProperties{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", version='" + version + '\'' +
                ", termOfServiceUrl='" + termOfServiceUrl + '\'' +
                ", contact='" + contact + '\'' +
                ", license='" + license + '\'' +
                ", licenseUrl='" + licenseUrl + '\'' +
                ", includePattern='" + includePattern + '\'' +
                '}';
    }
}

/*
 * Copyright (C) 2005-2017 Qihoo 360 Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.qihoo360.replugin.gradle.host.creator.impl.java

import com.qihoo360.replugin.gradle.host.creator.IFileCreator

/**
 * @author RePlugin Team
 */
public class PluginPitProviderCreator implements IFileCreator {

    def static final FILE_PATH = '/com/qihoo360/replugin/component/provider/'
    def static final FILE_NAME_PREFIX = 'PluginPitProviderP'

    def config
    def project
    def variant
    def fileDir
    def fileName
    // 第n个进程
    def pn

    def PluginPitProviderCreator(def project, def variant, def cfg, def pn) {
        this.project = project
        this.variant = variant;
        this.config = cfg
        this.pn = pn
        //make it generated in buildConfig output dir so that we don't need to hook anything
        File buildConfigGeneratedDir = this.variant.getVariantData().getScope().getBuildConfigSourceOutputDir()
        fileName = FILE_NAME_PREFIX + pn + ".java"
        fileDir = new File(buildConfigGeneratedDir, FILE_PATH)
    }

    @Override
    String getFileName() {
        fileName
    }

    @Override
    File getFileDir() {
        fileDir
    }

    @Override
    String getFileContent() {
        return """
package com.qihoo360.replugin.component.provider;

/**
 * 注意：此文件由插件化框架自动生成，请不要手动修改。
 */
public class PluginPitProviderP${pn} extends PluginPitProviderBase {
    public static final String AUTHORITY = AUTHORITY_PREFIX + "${pn}";

    public PluginPitProviderP${pn}() {
        super(AUTHORITY);
    }
}"""
    }
}

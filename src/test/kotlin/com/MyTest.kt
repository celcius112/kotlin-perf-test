package com

import org.assertj.core.api.Assertions.assertThat
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test

class MyTest {

    @Test
    fun `should contain default plugins`() {
        val project = ProjectBuilder.builder().build()

        assertThat(project.plugins).isEmpty()
        project.plugins.apply("t")

        assertThat(project.pluginManager.hasPlugin("base")).isTrue()
        assertThat(project.pluginManager.hasPlugin("idea")).isTrue

        //assertThat(project.getTasksByName("generateGitProperties", false)).hasSize(1)
        //assertThat(project.getTasksByName("printVersion", false)).hasSize(1)
    }
}

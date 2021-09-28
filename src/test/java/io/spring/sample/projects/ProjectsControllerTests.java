package io.spring.sample.projects;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.boot.test.tester.AutoConfigureGraphQlTester;
import org.springframework.graphql.test.tester.WebGraphQlTester;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureGraphQlTester
class ProjectsControllerTests {

	@Autowired
	private WebGraphQlTester graphQlTester;

	@Test
	void shouldListReleaseRepositories() {
		this.graphQlTester.query("""
				{
				  project(slug: "spring-graphql") {
					name
					releases {
					  version
					  repository {
						id
					  }
					}
				  }
				}
				""")
				.execute().path("project.releases[*].repository.id")
				.entityList(String.class).contains("spring-milestones", "spring-snapshots");
	}
}
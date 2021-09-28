package io.spring.sample.projects.greeting;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.boot.test.tester.AutoConfigureGraphQlTester;
import org.springframework.graphql.test.tester.WebGraphQlTester;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureGraphQlTester
class GreetingControllerTests {

	@Autowired
	private WebGraphQlTester webGraphQlTester;

	@Test
	void shouldGreetWithSpecificName() {
		this.webGraphQlTester.query("""
				{
				  greeting(name: "Brian")
				}
				""")
				.execute().path("greeting").entity(String.class).isEqualTo("Hello, Brian!");
	}

	@Test
	void shouldGreetWithDefaultName() {
		this.webGraphQlTester.query("""
				{
				  greeting
				}
				""")
				.execute().path("greeting").entity(String.class).isEqualTo("Hello, Spring!");
	}
}
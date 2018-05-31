package org.hesperides.tests.bdd.modules.scenarios;

import cucumber.api.java8.En;
import org.hesperides.domain.templatecontainer.entities.TemplateContainer;
import org.hesperides.presentation.io.ModuleIO;
import org.hesperides.tests.bdd.CucumberSpringBean;
import org.hesperides.tests.bdd.modules.ModuleAssertions;
import org.hesperides.tests.bdd.modules.contexts.ModuleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class ReleaseAModule extends CucumberSpringBean implements En {

    @Autowired
    private ModuleContext moduleContext;

    private ResponseEntity<ModuleIO> response;

    public ReleaseAModule() {
        When("^releasing the module$", () -> {
            response = moduleContext.releaseModule();
        });

        Then("^the module is released$", () -> {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            ModuleIO moduleOutput = response.getBody();
            assertEquals(1, moduleOutput.getVersionId().longValue());
            assertEquals(false, moduleOutput.isWorkingCopy());
        });
    }

    // TODO Tester avec technos et templates (copie profonde)
}

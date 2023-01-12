package seg3102.project.contracts

import io.cucumber.junit.platform.engine.Constants
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite

@Suite
@SelectClasspathResource("seg3102/PharmacyProjectCerealKillers/contracts")
@CucumberContextConfiguration
@ConfigurationParameter(
    key = Constants.GLUE_PROPERTY_NAME,
    value = "seg3102.PharmacyProjectCerealKillers.contracts"
)
class CucumberContracts {
}
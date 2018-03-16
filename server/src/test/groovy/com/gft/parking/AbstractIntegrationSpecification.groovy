package com.gft.parking

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared
import org.springframework.boot.context.properties.ConfigurationProperties
import spock.lang.Specification

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE)
@Transactional
@Rollback
@ConfigurationProperties("integration-tests")
class AbstractIntegrationSpecification extends Specification {

    // This field is set according to the application properties files for each environment
    @Shared def baseUrl

    @Shared TestRestTemplate template = new TestRestTemplate()

    def sessionHeader

    def exchange(url, method, payload, clazz) {
        def entity = payload == null ?
                new HttpEntity(sessionHeader) :
                new HttpEntity(payload, sessionHeader)
        return template.exchange(url, method, entity, clazz)
    }

    def delete(url) {
        exchange(url, HttpMethod.DELETE, null, String.class)
    }

    def getForEntity(url, clazz) {
        return exchange(url, HttpMethod.GET, null, clazz)
    }

    def postForEntity(url, payload, clazz) {
        return exchange(url, HttpMethod.POST, payload, clazz)
    }

    def postForLocation(url, payload) {
        def response = postForEntity(url, payload, String.class)
        if (response.statusCode != HttpStatus.CREATED)
            return null
        return response.getHeaders().getLocation()
    }

    def put(url, payload) {
        template.put(url, new HttpEntity(payload, sessionHeader))
    }

    def putForResponseEntity(url, payload) {
        return exchange(url, HttpMethod.PUT, payload, String.class)
    }
}

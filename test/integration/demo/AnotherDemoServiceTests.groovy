package demo

import grails.plugin.multitenant.core.Tenant;
import grails.test.*

import org.junit.Test

/**
 * Verify that a tenant-scoped proxy is created around
 * beans configured as per-tenant using Grails convention (static scope field). 
 * @author Kim A. Betti <kim@developer-b.com>
 */
class AnotherDemoServiceTests extends GrailsUnitTestCase {

    def anotherDemoService

    @Test
    void shouldCreateProxyForPerTenantBeans() {
        Tenant.withTenantId 1, {
            assertEquals "none", anotherDemoService.touchedByTenant
            anotherDemoService.touchedByTenant = "Tenant-1"
        }

        Tenant.withTenantId 2, {
            assertEquals "none", anotherDemoService.touchedByTenant
            anotherDemoService.touchedByTenant = "Tenant-2"
        }

        Tenant.withTenantId 1, { 
            assertEquals "Tenant-1", anotherDemoService.touchedByTenant
        }
    }
    
}
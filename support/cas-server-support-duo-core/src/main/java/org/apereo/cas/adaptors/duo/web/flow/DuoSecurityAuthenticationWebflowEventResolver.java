package org.apereo.cas.adaptors.duo.web.flow;

import org.apereo.cas.CentralAuthenticationService;
import org.apereo.cas.authentication.AuthenticationServiceSelectionPlan;
import org.apereo.cas.authentication.AuthenticationSystemSupport;
import org.apereo.cas.authentication.MultifactorAuthenticationProviderSelector;
import org.apereo.cas.services.ServicesManager;
import org.apereo.cas.ticket.registry.TicketRegistrySupport;
import org.apereo.cas.web.flow.authentication.BaseMultifactorAuthenticationProviderEventResolver;

import org.apereo.inspektr.audit.annotation.Audit;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.util.CookieGenerator;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import java.util.Set;

/**
 * This is {@link DuoSecurityAuthenticationWebflowEventResolver }.
 *
 * @author Misagh Moayyed
 * @since 5.0.0
 */
public class DuoSecurityAuthenticationWebflowEventResolver extends BaseMultifactorAuthenticationProviderEventResolver {

    public DuoSecurityAuthenticationWebflowEventResolver(final AuthenticationSystemSupport authenticationSystemSupport,
                                                         final CentralAuthenticationService centralAuthenticationService, final ServicesManager servicesManager,
                                                         final TicketRegistrySupport ticketRegistrySupport, final CookieGenerator warnCookieGenerator,
                                                         final AuthenticationServiceSelectionPlan authenticationSelectionStrategies,
                                                         final MultifactorAuthenticationProviderSelector selector,
                                                         final ApplicationEventPublisher eventPublisher,
                                                         final ConfigurableApplicationContext applicationContext) {
        super(authenticationSystemSupport, centralAuthenticationService,
            servicesManager, ticketRegistrySupport, warnCookieGenerator,
            authenticationSelectionStrategies, selector, eventPublisher, applicationContext);
    }

    @Override
    public Set<Event> resolveInternal(final RequestContext requestContext) {
        return handleAuthenticationTransactionAndGrantTicketGrantingTicket(requestContext);
    }

    @Audit(action = "AUTHENTICATION_EVENT",
        actionResolverName = "AUTHENTICATION_EVENT_ACTION_RESOLVER",
        resourceResolverName = "AUTHENTICATION_EVENT_RESOURCE_RESOLVER")
    @Override
    public Event resolveSingle(final RequestContext context) {
        return super.resolveSingle(context);
    }
}


package com.bankaccount.services;

import com.bankaccount.FindBankAccountQuery;
import com.bankaccount.entities.Account;
import lombok.AllArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class AccountQueryService {
    private final QueryGateway queryGateway;
    private final EventStore eventStore;
    public CompletableFuture<Account> findById(String accountId) {
        return this.queryGateway.query(
                new FindBankAccountQuery(format(accountId)),
                ResponseTypes.instanceOf(Account.class)
        );
    }

    public List<Object> listEventsForAccount(String accountId) {
        return this.eventStore
                .readEvents(format(accountId.toString()))
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }
}

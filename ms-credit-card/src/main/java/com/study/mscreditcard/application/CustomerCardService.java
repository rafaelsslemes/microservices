package com.study.mscreditcard.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.mscreditcard.application.exception.InexistentCreditCardException;
import com.study.mscreditcard.domain.CreditCard;
import com.study.mscreditcard.domain.CreditCardIssueData;
import com.study.mscreditcard.domain.CustomerCard;
import com.study.mscreditcard.repository.CustomerCardRepository;

@Service
public class CustomerCardService {

    @Autowired
    private CustomerCardRepository repository;

    @Autowired
    private CreditCardService creditCardService;
    
    public List<CustomerCard> getCardByDoc(String doc){
        return repository.findByDoc(doc);
    }

    public CustomerCard issueCreditCard(CreditCardIssueData issueData) throws InexistentCreditCardException{

        Optional<CreditCard> optionalCreditCard = creditCardService.findById(issueData.getCardId());
        
        if (!optionalCreditCard.isPresent()){
            throw new InexistentCreditCardException();
        }

        CreditCard creditCard = optionalCreditCard.get();

        CustomerCard cardIssued = new CustomerCard();
        cardIssued.setCreditCard(creditCard);
        cardIssued.setDoc(issueData.getCustomerDoc());
        cardIssued.setCardLimit(issueData.getCardLimit());
        
        cardIssued = repository.save(cardIssued);
        return cardIssued;
    }
}

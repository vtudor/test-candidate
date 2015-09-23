package com.test.candidate.service;

import com.test.candidate.service.dto.CandidateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Tudor on 23-Sep-15.
 */
@Service
public class NotificationServiceImpl implements NotificationService
{

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void addCandidate(CandidateDto dto)
    {
        jmsTemplate.convertAndSend("candidate.queue", dto);
    }
}

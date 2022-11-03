package com.codegym.service.withdraw;

import com.codegym.model.WithDraw;
import com.codegym.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class WithdrawService implements IWithdrawService{
    @Autowired
    private WithdrawRepository withdrawRepository ;
    @Override
    public List<WithDraw> findAll() {
        return withdrawRepository.findAll();
    }

    @Override
    public WithDraw getById(Long id) {
        return withdrawRepository.getById(id);
    }

    @Override
    public Optional<WithDraw> findById(Long id) {
        return withdrawRepository.findById(id);
    }

    @Override
    public WithDraw save(WithDraw withDraw) {
        return withdrawRepository.save(withDraw);
    }

    @Override
    public void remove(Long id) {
        withdrawRepository.deleteById(id);
    }
}

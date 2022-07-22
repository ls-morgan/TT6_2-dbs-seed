package com.dbs.services;

import com.dbs.controllers.requests.WalletRequest;
import com.dbs.entities.User;
import com.dbs.entities.Wallet;
import com.dbs.exceptions.WalletNotFoundException;
import com.dbs.repositories.UserRepository;
import com.dbs.repositories.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public List<Wallet> getWalletByUser(int userId) {
        log.info(String.format("Getting wallet id %s", userId));
        return walletRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<Wallet> getAllWallets() {
        log.info("Getting all wallets");
        return walletRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Wallet getWallet(int id) {
        log.info(String.format("Getting wallet id %s", id));
        return walletRepository.findById(id).orElseThrow(
                () -> new WalletNotFoundException(String.format("Wallet id %s does not exist", id)));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Wallet createWallet(WalletRequest walletRequest) {
        User user = userService.getUser(walletRequest.getUserId());
        Wallet wallet = Wallet.builder()
                .name(walletRequest.getName())
                .userId(user.getId())
                .build();
        return walletRepository.save(wallet);
    }

    @Transactional
    public Wallet updateWallet(Wallet wallet, int id) {
        log.info(String.format("Updating wallet id %s", id));
        Wallet existingWallet = getWallet(id);
        existingWallet.setName(wallet.getName());
        return walletRepository.save(existingWallet);
    }

    public void deleteWallet(int id) {
        log.info(String.format("Deleting wallet id %s", id));
        Wallet existingWallet = getWallet(id);
        walletRepository.delete(existingWallet);
    }

}

package com.xuanfeiyang.erp.service.impl;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.service.AccountService;

/**
 * 
 * @author bernard
 *
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

//	@Autowired
//	private AccountDao accountDao;
//
//	public Long add(Account account) {
//		accountDao.insert(account);
//		return account.getId();
//	}
//
//	public void modify(Account account) {
//		accountDao.update(account);
//	}
//
//	public void remove (Long id) {
//		accountDao.delete(id);
//	}
//
//	public Account get(Long id) {
//		return accountDao.get(id);
//	}
//
//	@Override
//	public Account getByAccountName(String accountName) {
//		return this.accountDao.getByAccountName(accountName);
//	}
//
//	public List<Account> find(AccountParam param) {
//		return accountDao.find(param);
//	}
//
//	@Override
//	public Long findCount(AccountParam param) {
//		return accountDao.findCount(param);
//	}
//
//	public void setAccountDao(AccountDao accountDao) {
//		this.accountDao = accountDao;
//	}
//
//	@Override
//	public Page<Account> findPage(PageRequest pageRequest,AccountParam param) {
//		return accountDao.findPage(pageRequest,param);
//	}
//
//	@Override
//	public EubConfig getEubConfigByActId(Long accountId,Integer eubType) {
//		EubConfig cfg =  accountDao.getEubConfigByActId(accountId,eubType);
//		//根据eubId获取eubAddress
//		if (cfg==null || cfg.getId()==null){
//			return null;
//		}
//		List<EubAddress> addressList = accountDao.getEubAddressByEcgId(cfg.getId());
//		for(EubAddress address:addressList){
//			if (address.getAddressType()==0){
//				cfg.setConsigneeAdd(address);
//			}else if (address.getAddressType()==1){
//				cfg.setRejectedenAdd(address);
//			}else if(address.getAddressType()==2){
//				cfg.setRejectedcnAdd(address);
//			}
//		}
//		return cfg;
//	}
//	
//
//	@Override
//	public EubConfig getEubConfigByAccountName(String accountName, Integer eubType) {
//		EubConfig cfg =  accountDao.getEubConfigByAccountName(accountName,eubType);
//		//根据eubId获取eubAddress
//		if (cfg==null || cfg.getId()==null){
//			return null;
//		}
//		List<EubAddress> addressList = accountDao.getEubAddressByEcgId(cfg.getId());
//		for(EubAddress address:addressList){
//			if (address.getAddressType()==0){
//				cfg.setConsigneeAdd(address);
//			}else if (address.getAddressType()==1){
//				cfg.setRejectedenAdd(address);
//			}else if(address.getAddressType()==2){
//				cfg.setRejectedcnAdd(address);
//			}
//		}
//		return cfg;
//	}
//
//	@Override
//	public EubAddress getEubAddressByActAndType(String accountId,
//			Integer eubType, Integer eubaddressType) {
//		return accountDao.getEubAddressByActAndType(accountId, eubType, eubaddressType);
//	}
//
//	@Override
//	@Transactional
//	public Long addEubConfig(EubConfig eub) {
//		accountDao.addEubConfig(eub);
//		if (eub.getConsigneeAdd() != null) {
//			eub.getConsigneeAdd().setEubId(eub.getId());
//			accountDao.addEubAddress(eub.getConsigneeAdd());
//		}
//		if (eub.getRejectedcnAdd() != null) {
//			eub.getRejectedcnAdd().setEubId(eub.getId());
//			accountDao.addEubAddress(eub.getRejectedcnAdd());
//		}
//		if (eub.getRejectedenAdd() != null) {
//			eub.getRejectedenAdd().setEubId(eub.getId());
//			accountDao.addEubAddress(eub.getRejectedenAdd());
//		}
//		return eub.getId();
//	}
//
//	@Override
//	@Transactional
//	public void removeEubConfig(Long id) {
//		accountDao.removeEubConfig(id);
//		List<EubAddress> addList = accountDao.getEubAddressByEcgId(id);
//		accountDao.removeEubAddress(addList);
//	}

}

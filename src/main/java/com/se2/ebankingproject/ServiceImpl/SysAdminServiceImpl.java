package com.se2.ebankingproject.ServiceImpl;

import com.se2.ebankingproject.Service.SysAdminService;
import com.se2.ebankingproject.Model.SysAdmin;
import com.se2.ebankingproject.Repository.SysAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysAdminServiceImpl implements SysAdminService {

    @Autowired
    SysAdminRepository sysAdminRepository;

    @Override
    public SysAdmin getSysAdminById(int sysAdminId) {

        return sysAdminRepository.findById(sysAdminId).get();
    }

}


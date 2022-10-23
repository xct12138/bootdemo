package com.xct.bootdemo

import com.xct.bootdemo.service.CartoonService
import com.xct.bootdemo.util.insert
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CartoonDataInsertTest{

    @Test
    fun test(@Autowired ctService: CartoonService){
        //1199,1200,1201,1480-1676
        insert(1480,ctService)
    }


}

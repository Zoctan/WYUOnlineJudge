package com.zoctan.api;

import com.zoctan.api.core.jwt.JWTSetting;
import com.zoctan.api.util.RSAUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * @author Zoctan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RSASignTest {
    @Resource
    private RSAUtil rsaUtil;
    @Resource
    private JWTSetting jwtSetting;

    /**
     * 加载公私钥pem格式文件测试
     */
    @Test
    public void test1() throws Exception {
        final PublicKey publicKey = this.rsaUtil.loadPemPublicKey(this.jwtSetting.getPublicKey());
        final PrivateKey privateKey = this.rsaUtil.loadPemPrivateKey(this.jwtSetting.getPrivateKey());
        Assert.assertNotNull(publicKey);
        Assert.assertNotNull(privateKey);
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);
    }

    /**
     * 生成RSA密钥对并进行加解密测试
     */
    @Test
    public void test2() throws Exception {
        final String data = "hello word";
        final KeyPair keyPair = this.rsaUtil.genKeyPair(512);

        // 获取公钥，并以base64格式打印出来
        final PublicKey publicKey = keyPair.getPublic();
        System.out.println("公钥：" + new String(Base64.getEncoder().encode(publicKey.getEncoded())));

        // 获取私钥，并以base64格式打印出来
        final PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("私钥：" + new String(Base64.getEncoder().encode(privateKey.getEncoded())));

        // 公钥加密
        final byte[] encryptedBytes = this.rsaUtil.encrypt(data.getBytes(), publicKey);
        System.out.println("加密后：" + new String(encryptedBytes));

        // 私钥解密
        final byte[] decryptedBytes = this.rsaUtil.decrypt(encryptedBytes, privateKey);
        System.out.println("解密后：" + new String(decryptedBytes));
    }
}
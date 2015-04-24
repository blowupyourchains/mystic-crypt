/**
 * Copyright (C) 2007 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.crypto.sha;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import de.alpharogroup.crypto.aes.HexEncryptor;
import de.alpharogroup.crypto.algorithm.HashAlgorithm;
import net.sourceforge.jaulp.random.Constants;
import net.sourceforge.jaulp.random.RandomUtils;

import org.apache.commons.codec.binary.Base64;

/**
 * The Class Hasher.
 *
 * @author Asterios Raptis
 */
public class Hasher
{

	/**
	 * Hash.
	 *
	 * @param hashIt
	 *            the hash it
	 * @param salt
	 *            the salt
	 * @param hashAlgorithm
	 *            the hash algorithm
	 * @param charset
	 *            the charset
	 * @return the string
	 * @throws NoSuchAlgorithmException
	 *             is thrown if instantiation of the MessageDigest object fails.
	 */
	public static String hash(String hashIt, String salt, HashAlgorithm hashAlgorithm,
		Charset charset) throws NoSuchAlgorithmException
	{
		MessageDigest messageDigest = MessageDigest.getInstance(hashAlgorithm.getAlgorithm());
		messageDigest.reset();
		messageDigest.update(salt.getBytes(charset));
		return new String(messageDigest.digest(hashIt.getBytes(charset)), charset);
	}

	/**
	 * Hash and hex.
	 *
	 * @param hashIt
	 *            the hash it
	 * @param salt
	 *            the salt
	 * @param hashAlgorithm
	 *            the hash algorithm
	 * @param charset
	 *            the charset
	 * @return the string
	 * @throws NoSuchAlgorithmException
	 *             is thrown if instantiation of the MessageDigest object fails.
	 * @throws UnsupportedEncodingException
	 *             is thrown by get the byte array of the private key String object fails.
	 * @throws NoSuchPaddingException
	 *             is thrown if instantiation of the cypher object fails.
	 * @throws InvalidKeyException
	 *             the invalid key exception is thrown if initialization of the cypher object fails.
	 * @throws BadPaddingException
	 *             is thrown if {@link Cipher#doFinal(byte[])} fails.
	 * @throws IllegalBlockSizeException
	 *             is thrown if {@link Cipher#doFinal(byte[])} fails.
	 */
	public static String hashAndHex(String hashIt, String salt, HashAlgorithm hashAlgorithm,
		Charset charset) throws NoSuchAlgorithmException, InvalidKeyException,
		UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException,
		BadPaddingException
	{
		HexEncryptor hexEncryptor = new HexEncryptor();
		return hexEncryptor.encrypt(hash(hashIt, salt, hashAlgorithm, charset));
	}

	public static String hashAndBase64(String hashIt, String salt, HashAlgorithm hashAlgorithm,
		Charset charset) throws NoSuchAlgorithmException
	{
		String hashedAndBase64 = new Base64().encodeToString(hash(hashIt, salt, hashAlgorithm,
			charset).getBytes(charset));
		return hashedAndBase64;
	}

	/**
	 * Gets the random salt.
	 *
	 * @param length
	 *            the length
	 * @param charset
	 *            the charset
	 * @return the random salt
	 */
	public static byte[] getRandomSalt(int length, Charset charset)
	{
		return RandomUtils.getRandomString(Constants.LCUCCHARSWN, length).getBytes(charset);
	}

}

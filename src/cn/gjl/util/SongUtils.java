package cn.gjl.util;

public class SongUtils {

	public static String base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	public static int[] base64DecodeChars = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1,
			-1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8,
			9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29,
			30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1,
			-1 };

	public static String base64decode(String str) {
		int len = str.length();
		int i = 0;
		String out = "";
		while (i < len) {
			int c1;
			do {
				c1 = base64DecodeChars[(getChar(str, i++) & 0xFF)];
			} while ((i < len) && (c1 == -1));
			if (c1 == -1) {
				break;
			}
			int c2;
			do {
				c2 = base64DecodeChars[(getChar(str, i++) & 0xFF)];
			} while ((i < len) && (c2 == -1));
			if (c2 == -1) {
				break;
			}
			out = out + getChar2(c1 << 2 | (c2 & 0x30) >> 4);
			int c3;
			do {
				c3 = getChar(str, i++) & 0xFF;
				if (c3 == 61) {
					return out;
				}
				c3 = base64DecodeChars[c3];
			} while ((i < len) && (c3 == -1));
			if (c3 == -1) {
				break;
			}
			out = out + getChar2((c2 & 0xF) << 4 | (c3 & 0x3C) >> 2);
			int c4;
			do {
				c4 = getChar(str, i++) & 0xFF;
				if (c4 == 61) {
					return out;
				}
				c4 = base64DecodeChars[c4];
			} while ((i < len) && (c4 == -1));
			if (c4 == -1) {
				break;
			}
			out = out + getChar2((c3 & 0x3) << 6 | c4);
		}
		return out;
	}

	public static String jiemi(String str) {
		String str1 = str.replace("imusic://", "");
		String str2 = base64decode(str1);
		return str2.replace("AA", "").replace("ZZ", "");
	}

	public static int getChar(String str, int index) {
		int cstr = str.charAt(index);
		return cstr;
	}

	public static String getChar2(int i) {
		char c = (char) i;
		return c + "";
	}

}

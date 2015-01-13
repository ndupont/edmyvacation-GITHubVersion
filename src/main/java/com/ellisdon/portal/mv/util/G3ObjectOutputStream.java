package com.ellisdon.portal.mv.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.ellisdon.portal.mv.logger.MyVacationLogger;

/**
 *
 * @author LGS/ndupont
 * @version 1.0 2011-10-13
 */
public class G3ObjectOutputStream extends ObjectOutputStream {
	protected static final Logger logger = MyVacationLogger.getLogger(G3ObjectOutputStream.class);
	private ObjectOutputStream oos;
	private OutputStream outputstream;
	/**
	 * Constructor of G3ObjectOutputStream
	 * @throws IOException
	 * @throws SecurityException
	 */
	public G3ObjectOutputStream() throws IOException, SecurityException {
		super();
	}

	/**
	 * Constructor of G3ObjectOutputStream
	 * @param out
	 * @throws IOException
	 */
	public G3ObjectOutputStream(OutputStream out) throws IOException {
		super();
		oos=new ObjectOutputStream(out) ;
		outputstream=out;
	}
    /**
     * Method used by subclasses to override the default writeObject method.
     * This method is called by trusted subclasses of ObjectInputStream that
     * constructed ObjectInputStream using the protected no-arg constructor.
     * The subclass is expected to provide an override method with the modifier
     * "final".
     *
     * @param	obj object to be written to the underlying stream
     * @throws	IOException if there are I/O errors while writing to the
     * 		underlying stream
     * @see #ObjectOutputStream()
     * @see #writeObject(Object)
     * @since 1.2
     */
    public void writeObjectOverride(Object obj) throws IOException {
    	int size=0;
    	int idxbegin=0;
    	int idxend=0;
    	if(outputstream instanceof ByteArrayOutputStream){
    		idxbegin = ((ByteArrayOutputStream)outputstream).size();
    	}
    	oos.writeObject(obj);
    	if(outputstream instanceof ByteArrayOutputStream){
    		idxend = ((ByteArrayOutputStream)outputstream).size();
    	}
    	size=idxend-idxbegin;
//		logger.info("Object "+obj.getClass().getName()+" size: "+size+ "B" );
    }
    
    public void close() throws IOException {
    	oos.close();
    }
    /**
     * Writes a boolean.
     *
     * @param	val the boolean to be written
     * @throws	IOException if I/O errors occur while writing to the underlying
     * 		stream
     */
    public void writeBoolean(boolean val) throws IOException {
	oos.writeBoolean(val);
    }

    /**
     * Writes an 8 bit byte.
     *
     * @param	val the byte value to be written
     * @throws	IOException if I/O errors occur while writing to the underlying
     * 		stream
     */
    public void writeByte(int val) throws IOException  {
	oos.writeByte(val);
    }

    /**
     * Writes a 16 bit short.
     *
     * @param	val the short value to be written
     * @throws	IOException if I/O errors occur while writing to the underlying
     * 		stream
     */
    public void writeShort(int val)  throws IOException {
	oos.writeShort(val);
    }

    /**
     * Writes a 16 bit char.
     *
     * @param	val the char value to be written
     * @throws	IOException if I/O errors occur while writing to the underlying
     * 		stream
     */
    public void writeChar(int val)  throws IOException {
	oos.writeChar(val);
    }

    /**
     * Writes a 32 bit int.
     *
     * @param	val the integer value to be written
     * @throws	IOException if I/O errors occur while writing to the underlying
     * 		stream
     */
    public void writeInt(int val)  throws IOException {
	oos.writeInt(val);
    }

    /**
     * Writes a 64 bit long.
     *
     * @param	val the long value to be written
     * @throws	IOException if I/O errors occur while writing to the underlying
     * 		stream
     */
    public void writeLong(long val)  throws IOException {
	oos.writeLong(val);
    }

    /**
     * Writes a 32 bit float.
     *
     * @param	val the float value to be written
     * @throws	IOException if I/O errors occur while writing to the underlying
     * 		stream
     */
    public void writeFloat(float val) throws IOException {
	oos.writeFloat(val);
    }

    /**
     * Writes a 64 bit double.
     *
     * @param	val the double value to be written
     * @throws	IOException if I/O errors occur while writing to the underlying
     * 		stream
     */
    public void writeDouble(double val) throws IOException {
	oos.writeDouble(val);
    }

    /**
     * Writes a String as a sequence of bytes.
     *
     * @param	str the String of bytes to be written
     * @throws	IOException if I/O errors occur while writing to the underlying
     * 		stream
     */
    public void writeBytes(String str) throws IOException {
	oos.writeBytes(str);
    }

    /**
     * Writes a String as a sequence of chars.
     *
     * @param	str the String of chars to be written
     * @throws	IOException if I/O errors occur while writing to the underlying
     * 		stream
     */
    public void writeChars(String str) throws IOException {
	oos.writeChars(str);
    }

    /**
     * Primitive data write of this String in 
     * <a href="DataInput.html#modified-utf-8">modified UTF-8</a>
     * format.  Note that there is a
     * significant difference between writing a String into the stream as
     * primitive data or as an Object. A String instance written by writeObject
     * is written into the stream as a String initially. Future writeObject()
     * calls write references to the string into the stream.
     *
     * @param	str the String to be written
     * @throws	IOException if I/O errors occur while writing to the underlying
     * 		stream
     */
    public void writeUTF(String str) throws IOException {
    	oos.writeUTF(str);
    }

}

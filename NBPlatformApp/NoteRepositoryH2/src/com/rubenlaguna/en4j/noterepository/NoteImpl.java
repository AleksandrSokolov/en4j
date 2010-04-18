/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubenlaguna.en4j.noterepository;

import com.rubenlaguna.en4j.noteinterface.Note;
import com.rubenlaguna.en4j.noteinterface.Resource;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.sql.Connection;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.util.Lookup;

/**
 *
 * @author Ruben Laguna <ruben.laguna@gmail.com>
 */
class NoteImpl implements Note {

    private final int id;

    NoteImpl(int id) {
        this.id = id;
    }

    public String getContent() {
        final Reader characterStream = getContentAsReader();
        if (null == characterStream) {
            return "";
        }
        CharBuffer cb = CharBuffer.allocate(64000);
        StringBuffer sb = new StringBuffer();
        try {
            while (characterStream.ready()) {
                cb.clear();
                characterStream.read(cb);
                cb.flip();
                sb.append(cb);
            }
        } catch (IOException e) {
            getLogger().log(Level.WARNING, "caught exception:", e);
        } finally {
            try {
                characterStream.close();
            } catch (IOException e) {
            }
        }
        return sb.toString();
    }

    public Reader getContentAsReader() {
        return DbPstmts.getInstance().getContentAsReader(id);
    }

    public void setContent(String content) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date getCreated() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCreated(Date created) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getSourceurl() {
        return DbPstmts.getInstance().getSourceurl(id);
    }

    public void setSourceurl(String sourceurl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getTitle() {
        return DbPstmts.getInstance().getTitle(id);
    }

    public void setTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date getUpdated() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setUpdated(Date updated) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getUpdateSequenceNumber() {
        return DbPstmts.getInstance().getUpdateSequenceNumber(id);
    }

    public boolean isActive() {
        return DbPstmts.getInstance().isActive(id);
    }

    public void setActive(boolean active) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date getDeleted() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDeleted(Date deleted) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Resource getResource(String hash) {
        final String guid = getGuid();
        if (null == guid) {
            return null;
        }
        if (hash.length()<32) {
            getLogger().warning("Hash should be 32 char long. Left padding it with zeros");
            StringBuffer sb = new StringBuffer();
            for(int i =hash.length();i<32;i++) {
                sb.append("0");
            }
            sb.append(hash);
            final String paddedHash = sb.toString();
            getLogger().warning("changed from "+hash+" ("+hash.length()+") to "+paddedHash+" ("+paddedHash.length()+")");
            getResource(paddedHash);
        }
        if (hash.length() != 32) {
            throw new IllegalArgumentException("hash has to be 32 bytes long. this "+hash+" was "+hash.length());
        }
        return Lookup.getDefault().lookup(NoteRepositoryH2Impl.class).getResource( guid, hash);
    }

    @Override
    public Collection<Resource> getResources() {
        Set<Resource> toReturn = new HashSet<Resource>();
        final String guid = getGuid();
        if (null == guid) {
            return Collections.EMPTY_LIST;
        }
        Collection<String> resHashes = DbPstmts.getInstance().getResources(guid);
        for (String hash : resHashes) {
            toReturn.add(getResource(hash));
        }
        return toReturn;
    }

    public String getGuid() {
        return DbPstmts.getInstance().getGuid(id);
    }

    private Connection getConnection() {
        return Installer.c;
    }

    private Logger getLogger() {
        return Logger.getLogger(NoteImpl.class.getName());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author chieko Yamamoto
 */
@Embeddable
public class GroupKey implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String groupId;
    private String cid;

    public GroupKey() {
    }
    public GroupKey( String groupId,String cid) {
        this.groupId = groupId;
        this.cid = cid;
    }

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.cid);
		hash = 97 * hash + Objects.hashCode(this.groupId);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final GroupKey other = (GroupKey) obj;
		if (!Objects.equals(this.cid, other.cid)) {
			return false;
		}
		if (!Objects.equals(this.groupId, other.groupId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "GroupKey{" + "cid=" + cid + ", groupId=" + groupId + '}';
	}
        
}
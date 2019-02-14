package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Shariar
 */
@Entity
@Table(name = "username", catalog = "ScoreDB", schema = "")
@NamedQueries({
        @NamedQuery(name = "Username.findAll", query = "SELECT u FROM Username u")
        , @NamedQuery(name = "Username.findByPlayerId", query = "SELECT u FROM Username u WHERE u.playerid = :playerid")
        , @NamedQuery(name = "Username.findByUsername", query = "SELECT u FROM Username u WHERE u.username = :username")})
public class Username implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Player_id")
    private Integer playerid;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @JoinColumn(name = "Player_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Player player;

    public Username() {
    }

    public Username(Integer playerid) {
        this.playerid = playerid;
    }

    public Username(Integer playerid, String username) {
        this.playerid = playerid;
        this.username = username;
    }

    public Username(Integer playerid, String username, Player player) {
        this.playerid = playerid;
        this.username = username;
        this.player = player;
    }

    public Integer getPlayerid() {
        return playerid;
    }

    public void setPlayerid(Integer playerid) {
        this.playerid = playerid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (playerid != null ? playerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Username)) {
            return false;
        }
        Username other = (Username) object;
        if ((this.playerid == null && other.playerid != null) || (this.playerid != null && !this.playerid.equals(other.playerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Username[ playerid=" + playerid + " ]";
    }

}

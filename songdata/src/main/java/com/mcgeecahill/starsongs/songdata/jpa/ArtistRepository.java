package com.mcgeecahill.starsongs.songdata.jpa;

import com.mcgeecahill.starsongs.songdata.domain.Artist;
import com.mcgeecahill.starsongs.songdata.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    // Native Query
    @Query(value = "SELECT * FROM Artist WHERE name LIKE Concat('%', :substr, '%')", nativeQuery = true)
    List<Artist> findByNameContains(@Param("substr") String substr);
}

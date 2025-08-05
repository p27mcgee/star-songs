package com.mcgeecahill.starsongs.songdata.jpa;

import com.mcgeecahill.starsongs.songdata.domain.Artist;
import com.mcgeecahill.starsongs.songdata.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SongRepository extends JpaRepository<Song, Integer> {

    // JPQL Query
    @Query("SELECT s FROM Song s WHERE s.artist.name = :artistName")
    List<Song> findByArtistName(@Param("artistName") String artistName);

    // Native Query
    @Query(value = "SELECT * FROM Song WHERE released < :beforeDate", nativeQuery = true)
    List<Song> findSongsReleasedBefore(@Param("beforeDate") Date beforeDate);

    // Native Query
    @Query(value = "SELECT * FROM Song WHERE title LIKE Concat('%', :substr, '%')", nativeQuery = true)
    List<Artist> findByTitleContains(@Param("substr") String substr);
}

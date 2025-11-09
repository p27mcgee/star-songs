import { useState, useEffect } from 'react';
import './SongList.css';

function SongList() {
  const [songs, setSongs] = useState([]);
  const [artists, setArtists] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchSongs();
  }, []);

  const fetchSongs = async () => {
    try {
      // Fetch songs from Spring Boot proxy
      const songsResponse = await fetch('/api/songs');
      if (!songsResponse.ok) {
        throw new Error('Failed to fetch songs');
      }
      const songsData = await songsResponse.json();
      setSongs(songsData);

      // Fetch artists for each unique artistId
      const artistIds = [...new Set(songsData.map(song => song.artistId))];
      const artistPromises = artistIds.map(id =>
        fetch(`/api/artists/${id}`)
          .then(res => res.ok ? res.json() : null)
      );

      const artistsData = await Promise.all(artistPromises);
      const artistsMap = {};
      artistsData.forEach(artist => {
        if (artist) {
          artistsMap[artist.id] = artist.name;
        }
      });
      setArtists(artistsMap);
      setLoading(false);
    } catch (err) {
      setError(err.message);
      setLoading(false);
    }
  };

  if (loading) {
    return <div className="loading">Loading songs...</div>;
  }

  if (error) {
    return <div className="error">Error: {error}</div>;
  }

  return (
    <div className="song-list-container">
      <h1 className="title">Star Songs</h1>
      <div className="table-container">
        <table className="song-table">
          <thead>
            <tr>
              <th>Song</th>
              <th>Artist</th>
            </tr>
          </thead>
          <tbody>
            {songs.map(song => (
              <tr key={song.id}>
                <td>
                  <a
                    href={song.url}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="song-link"
                  >
                    {song.title}
                  </a>
                </td>
                <td>{artists[song.artistId] || 'Unknown Artist'}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default SongList;

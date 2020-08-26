import React, {useEffect, useState} from 'react';
import axios from 'axios';

export const Album = ({id, name}) => (<div key={id} className={"album"}>{name}</div>)

export const Albums = () => {
    const [albums, setAlbums] = useState([]);
    useEffect(() => {
        async function getAlbums() {
            const {data} = await axios.get(`${process.env.REACT_APP_BACKEND_URL}/albums`)
            setAlbums(data);
        }

        getAlbums();
    }, [])
    return (<div className={"albums"}>{albums.map(album => (<Album key={album.id} id={album.id} name={album.name}/>))}</div>);
}

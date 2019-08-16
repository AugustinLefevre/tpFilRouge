package com.tp.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.io.IOException;
import java.net.URLDecoder;

public class Image extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String fichierRequis = request.getPathInfo();
		if(fichierRequis == null || "/".equals(fichierRequis)) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		fichierRequis = URLDecoder.decode(fichierRequis, "UTF-8");
		File fichier = new File(this.getServletConfig().getInitParameter("chemin"), fichierRequis);
		if(!fichier.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		String type = getServletContext().getMimeType(fichier.getName());
		if(type == null) {
			type = "application/octet-stream";
		}
		response.reset();
		response.setBufferSize(10240);
		response.setContentType(type);
		response.setHeader("Content-Length", String.valueOf(fichier.length()));
		response.setHeader("Contoent-Disposition", "inline; filename=\"" + fichier.getName() + "\"");
		BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream( new FileInputStream( fichier ), 10240 );
            sortie = new BufferedOutputStream( response.getOutputStream(), 10240 );

            byte[] tampon = new byte[10240];
            int longueur;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }
        } finally {
            try {
                sortie.close();
            } catch ( IOException ignore ) {
            }
            try {
                entree.close();
            } catch ( IOException ignore ) {
            }
        }
	}

}

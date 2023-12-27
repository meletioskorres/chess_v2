package com.chess.gui;

import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;
import com.chess.gui.Table.MoveLog;
import com.google.common.primitives.Ints;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TakenPiecesPanel extends JPanel {

    private final JPanel northPanel;
    private final JPanel southPanel;

    private static final Color PANEL_COLOR = Color.decode("0xFDFE6") ;
    private static final Dimension TAKEN_PIECES_DIMENSION = new Dimension(40, 80);
    private static final EtchedBorder PANEL_BORDER = new EtchedBorder(EtchedBorder.RAISED);

    public TakenPiecesPanel() {
        super(new BorderLayout());
        setBackground(PANEL_COLOR);
        setBorder(PANEL_BORDER);
        this.northPanel = new JPanel(new GridLayout(8, 2));
        this.southPanel = new JPanel(new GridLayout(8, 2));
        this.northPanel.setBackground(PANEL_COLOR);
        this.southPanel.setBackground(PANEL_COLOR);
        this.add(this.northPanel, BorderLayout.NORTH);
        this.add(this.southPanel, BorderLayout.SOUTH);
        setPreferredSize(TAKEN_PIECES_DIMENSION);
    }

    public void redo(MoveLog moveLog) {

        this.southPanel.removeAll();
        this.northPanel.removeAll();

        final List<Piece> whiteTakenPieces = new ArrayList<>();
        final List<Piece> blackTakenPieces = new ArrayList<>();

        for (Move move : moveLog.getMoves()) {
            if (move.isAttack()) {
                final Piece takenPiece = move.getAttackedPiece();
                if (takenPiece.getPieceAlliance().isWhite()) {
                    whiteTakenPieces.add(takenPiece);
                } else if (takenPiece.getPieceAlliance().isBlack()) {
                    blackTakenPieces.add(takenPiece);
                }
            }
        }

        Collections.sort(whiteTakenPieces,
                (o1, o2) -> Ints.compare(o1.getPieceValue(), o2.getPieceValue()));
        Collections.sort(blackTakenPieces,
                (o1, o2) -> Ints.compare(o1.getPieceValue(), o2.getPieceValue()));

        for (final Piece takenPiece : whiteTakenPieces) {
            try {
                final BufferedImage image = ImageIO.read(new File("art/pieces/"
                        + takenPiece.getPieceAlliance().toString().charAt(0) + takenPiece.getPieceType().toString().charAt(0)));
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel();
                this.southPanel.add(imageLabel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (final Piece takenPiece : blackTakenPieces) {
            try {
                final BufferedImage image = ImageIO.read(new File("art/pieces/"
                        + takenPiece.getPieceAlliance().toString().charAt(0) + takenPiece.getPieceType().toString().charAt(0)));
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel();
                this.northPanel.add(imageLabel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

package com.sonicether.soundphysics.performance;

import net.minecraft.block.BlockState;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.Nullable;

public class SPHitResult extends HitResult {
    private final Direction side;
    private final BlockPos blockPos;
    private final boolean missed;
    private final boolean insideBlock;
    private final BlockState blockState;
    public final WorldChunk chunk;

    public static SPHitResult createMissed(Vec3d pos, Direction side, BlockPos blockPos, WorldChunk c) {
        return new SPHitResult(true, pos, side, blockPos, false, null, c);
    }

    public SPHitResult(BlockHitResult blockHitResult, BlockState bs, WorldChunk c) {
        super(blockHitResult.getPos());
        this.missed = false;//blockHitResult.getType() == Type.MISS;
        this.side = blockHitResult.getSide();
        this.blockPos = blockHitResult.getBlockPos();
        this.insideBlock = blockHitResult.isInsideBlock();
        this.blockState = bs;
        this.chunk = c;
    }

    public SPHitResult(boolean missed, Vec3d pos, Direction side, BlockPos blockPos, boolean insideBlock, BlockState bs, WorldChunk c) {
        super(pos);
        this.missed = missed;
        this.side = side;
        this.blockPos = blockPos;
        this.insideBlock = insideBlock;
        this.blockState = bs;
        this.chunk = c;
    }
    public static SPHitResult get(BlockHitResult bhr, BlockState bs, WorldChunk c){
        if (bhr == null) return null;
        return new SPHitResult(bhr, bs, c);
    }

    public BlockPos getBlockPos() {return this.blockPos;}
    public Direction getSide() {return this.side;}
    public Type getType() {return this.missed ? Type.MISS : Type.BLOCK;}
    public boolean isMissed() {return this.missed;}
    public boolean isInsideBlock() {return this.insideBlock;}
    public BlockState getBlockState() {return blockState;}
}